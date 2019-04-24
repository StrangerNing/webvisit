export function openUrl(e) {
  if (e.startsWith('http://')) {
    e = e.substring(7, e.length)
  } else if (e.startsWith('https://')) {
    e = e.substring(8, e.length)
  }
  const p = window.location.protocol
  const a = document.createElement('a')
  a.setAttribute('href', `${p}//${e}`)
  a.setAttribute('target', '_blank')
  a.click()
  document.getElementsByTagName('body')[0].appendChild(a)
}
