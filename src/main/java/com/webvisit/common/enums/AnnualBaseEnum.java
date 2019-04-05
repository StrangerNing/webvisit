package com.webvisit.common.enums;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/6
 */
public class AnnualBaseEnum {
    public AnnualBaseEnum() {

    }

    public enum AnnualStatusEnum {
        /**
         * 禁用
         */
        DISABLE(0, "禁用"),

        /**
         * 启用
         */
        ENABLE(1, "启用");

        private Integer code;

        private String msg;

        AnnualStatusEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static String getMsg(Integer code) {
            for (AnnualBaseEnum.AnnualStatusEnum annualStatusEnum : values()) {
                if (annualStatusEnum.code.equals(code)) {
                    return annualStatusEnum.msg;
                }
            }
            return "未知的操作类型";
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public enum AnnualAccumulateEnum {
        /**
         * 上一年年假不累积到下一年
         */
        DISABLE(0, "否"),

        /**
         * 上一年年假累积到下一年
         */
        ENABLE(1, "是");

        private Integer code;

        private String msg;

        AnnualAccumulateEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static String getMsg(Integer code) {
            for (AnnualBaseEnum.AnnualAccumulateEnum annualAccumulateEnum : values()) {
                if (annualAccumulateEnum.code.equals(code)) {
                    return annualAccumulateEnum.msg;
                }
            }
            return "未知的操作类型";
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public enum AnnualProbationHasEnum {
        /**
         * 试用期不享有年假
         */
        DISABLE(0, "否"),

        /**
         * 试用期享有年假
         */
        ENABLE(1, "是");

        private Integer code;

        private String msg;

        AnnualProbationHasEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static String getMsg(Integer code) {
            for (AnnualBaseEnum.AnnualProbationHasEnum annualProbationHasEnum : values()) {
                if (annualProbationHasEnum.code.equals(code)) {
                    return annualProbationHasEnum.msg;
                }
            }
            return "未知的操作类型";
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public enum AnnualGraduationOneYearHasEnum {
        /**
         * 毕业未满一年不享有年假
         */
        DISABLE(0, "否"),

        /**
         * 毕业未满一年享有年假
         */
        ENABLE(1, "是");

        private Integer code;

        private String msg;

        AnnualGraduationOneYearHasEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static String getMsg(Integer code) {
            for (AnnualBaseEnum.AnnualGraduationOneYearHasEnum graduationOneYearHasEnum : values()) {
                if (graduationOneYearHasEnum.code.equals(code)) {
                    return graduationOneYearHasEnum.msg;
                }
            }
            return "未知的操作类型";
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
