package com.webvisit.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.webvisit.common.component.Column;
import com.webvisit.common.constant.LocalConstant;
import com.webvisit.common.enums.AttenceTypeEnum;
import com.webvisit.common.enums.PunchInEnum;
import com.webvisit.common.enums.PunchOutEnum;
import com.webvisit.common.exception.BusinessException;
import com.webvisit.model.vo.*;
import com.webvisit.service.AttenceService;
import com.webvisit.service.ExportService;
import com.webvisit.service.SysLogService;
import com.webvisit.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/24
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ExportServiceImpl implements ExportService {

    @Resource
    private AttenceService attenceService;
    @Resource
    private SysLogService sysLogService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private FastFileStorageClient fastFileStorageClient;

    @Override
    @Async
    public void exportPunchDetail(UserInfoVO userInfoVO, PunchDetailVO punchDetailVO, String uuid) {
        List<PunchDetailVO> punchDetailList = attenceService.queryAttencePunchDetailNoPaged(userInfoVO, punchDetailVO);
        List<PunchDetailExportVO> punchDetailExportList = convertPunchDetailVO2ExportFormat(punchDetailList);
        ArrayList<Column> columns = setPunchDetailColumns();
        String sheetName = "考勤明细表";
        HSSFWorkbook punchDetailWorkBook = ExcelUtil.generateExcel(columns, punchDetailExportList, sheetName);
        uploadWorkbook(punchDetailWorkBook,"考勤详情表",uuid);
    }

    @Override
    @Async
    public void exportLog(UserInfoVO userInfoVO, LogVO logVO, String uuid) {
        List<LogVO> logList = sysLogService.queryLog(userInfoVO, logVO);
        List<LogExportVO> logExportList = convertLogVO2ExportFormat(logList);
        ArrayList<Column> columns = setLogColumns();
        String sheetName = "日志记录表";
        HSSFWorkbook logWorkBook = ExcelUtil.generateExcel(columns, logExportList, sheetName);
        uploadWorkbook(logWorkBook,"操作日志表",uuid);
    }

    @Override
    public String getDownloadUrl(String uuid) {
        String url = redisTemplate.opsForValue().get(uuid);
        if (url == null || url.isEmpty()) {
            return null;
        }
        return url;
    }

    private void uploadWorkbook(HSSFWorkbook workbook,String workbookName,String uuid) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            workbook.write(os);
            byte[] workbook2Byte = os.toByteArray();
            InputStream inputStream = new ByteArrayInputStream(workbook2Byte);
            os.close();
            StorePath path = fastFileStorageClient.uploadFile(inputStream, workbook2Byte.length, "xls", null);
            if (null == path) {
                throw new BusinessException("上传文件失败");
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileUrl = String.format("%s%s?filename=%s-%s.xls", LocalConstant.IMG_SERVER_ADDRESS, path.getFullPath(), dateFormat.format(new Date()),workbookName);
            redisTemplate.opsForValue().set(uuid, fileUrl, LocalConstant.EXPORT_FILE_EXPOSE_TIME, TimeUnit.HOURS);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<PunchDetailExportVO> convertPunchDetailVO2ExportFormat(List<PunchDetailVO> punchDetailList) {
        List<PunchDetailExportVO> punchDetailExportList = new ArrayList<>();
        SimpleDateFormat dateFormatWithYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormatWithYMD = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < punchDetailList.size(); i++) {
            PunchDetailVO punchDetail = punchDetailList.get(i);
            PunchDetailExportVO punchDetailExport = new PunchDetailExportVO();
            //序号
            punchDetailExport.setIndex(String.valueOf(i + 1));
            //职工姓名
            punchDetailExport.setNickname(punchDetail.getNickname());
            //部门
            punchDetailExport.setDeptName(punchDetail.getDeptName());
            //考勤时间
            Date punchTime = punchDetail.getPunchTime();
            punchDetailExport.setPunchTime(punchTime == null ? null : dateFormatWithYMD.format(punchTime));
            //签到时间
            Date punchInTime = punchDetail.getPunchInTime();
            punchDetailExport.setPunchInTime(punchInTime == null ? null : dateFormatWithYMDHMS.format(punchInTime));
            //签到状态
            punchDetailExport.setPunchInStatus(PunchInEnum.getMsg(punchDetail.getPunchInStatus()));
            //签退时间
            Date punchOutTime = punchDetail.getPunchOutTime();
            punchDetailExport.setPunchOutTime(punchOutTime == null ? null : dateFormatWithYMDHMS.format(punchOutTime));
            //签退状态
            punchDetailExport.setPunchOutStatus(PunchOutEnum.getMsg(punchDetail.getPunchOutStatus()));
            //考勤类型
            punchDetailExport.setPunchType(AttenceTypeEnum.getMsg(punchDetail.getPunchType()));

            punchDetailExportList.add(punchDetailExport);
        }
        return punchDetailExportList;
    }

    private List<LogExportVO> convertLogVO2ExportFormat(List<LogVO> logVOList) {
        List<LogExportVO> logExportList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < logVOList.size(); i++) {
            LogVO logVO = logVOList.get(i);
            LogExportVO logExportVO = new LogExportVO();
            //序号
            logExportVO.setIndex(String.valueOf(i + 1));
            //用户名
            logExportVO.setUsername(logVO.getUsername());
            //操作信息
            logExportVO.setOperation(logVO.getOperation());
            //调用方法
            logExportVO.setMethod(logVO.getMethod());
            //入参
            logExportVO.setParams(logVO.getParams());
            //操作人IP
            logExportVO.setIp(logVO.getIp());
            //操作时间
            Date createTime = logVO.getCreateTime();
            logExportVO.setCreateTime(createTime == null ? null : dateFormat.format(createTime));

            logExportList.add(logExportVO);
        }
        return logExportList;
    }

    private ArrayList<Column> setPunchDetailColumns() {
        return new ArrayList<Column>() {
            {
                add(new Column("index").title("序号"));
                add(new Column("nickname").title("职工姓名"));
                add(new Column("deptName").title("部门"));
                add(new Column("punchTime").title("考勤时间"));
                add(new Column("punchInTime").title("签到时间"));
                add(new Column("punchInStatus").title("签到状态"));
                add(new Column("punchOutTime").title("签退时间"));
                add(new Column("punchOutStatus").title("签退状态"));
                add(new Column("punchType").title("考勤类型"));
            }
        };
    }

    private ArrayList<Column> setLogColumns() {
        return new ArrayList<Column>() {
            {
                add(new Column("index").title("序号"));
                add(new Column("username").title("用户名"));
                add(new Column("operation").title("操作信息"));
                add(new Column("method").title("调用方法"));
                add(new Column("params").title("入参"));
                add(new Column("ip").title("操作人IP"));
                add(new Column("createTime").title("操作时间"));
            }
        };
    }
}
