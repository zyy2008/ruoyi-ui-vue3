import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'

export function getToken() {
  // 使用localStorage.getItem方法获取Token
  return localStorage.getItem(TokenKey);
}

export function setToken(token) {
  // 使用localStorage.setItem方法设置Token
  localStorage.setItem(TokenKey, token);
}

export function removeToken() {
  // 使用localStorage.removeItem方法移除Token
  localStorage.removeItem(TokenKey);
}

// export function getToken() {
//   return Cookies.get(TokenKey)
// }

// export function setToken(token) {
//   return Cookies.set(TokenKey, token)
// }

// export function removeToken() {
//   return Cookies.remove(TokenKey)
// }
