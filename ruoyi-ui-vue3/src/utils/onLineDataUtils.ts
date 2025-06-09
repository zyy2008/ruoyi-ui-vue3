import CryptoJS from "crypto-js";
import axios from "axios";

// 线上数据接口地址
const DeviceDataURL = "http://admin.yun-sense.com/v1/getDeviceData";

/**
 *
 * @param deviceNo 设备编号
 * @param params 请求参数
 * @param apiKey key
 */
export const getDeviceDataParams = (
  deviceNo: string,
  params: Object,
  apiKey: string
) => {
  const currentTimestamp = Date.now();
  //
  // 1. 按参数名ASCII码表顺序排序
  const sortedKeys = Object.keys(params).sort();

  // 2. 拼接参数名和参数值
  let paramString = "";
  sortedKeys.forEach((key) => {
    paramString += key + params[key];
  });

  // 3. 在字符串前加上时间戳，后加上API密钥
  const signString = currentTimestamp + paramString + apiKey;

  // 4. 进行MD5摘要并转换为十六进制（大写）
  const signature = CryptoJS.MD5(signString)
    .toString(CryptoJS.enc.Hex)
    .toUpperCase();
  let param = {
    deviceNo,
    timestamp: currentTimestamp,
    sign: signature,
  };
  // 5. 合并参数对象和API密钥对象
  return axios.create({
    baseURL: DeviceDataURL,
    timeout: 1000,
    headers: { "X-Custom-Header": "foobar" },
    method: "get",
    params: {
      query: param,
    },
  });
};
