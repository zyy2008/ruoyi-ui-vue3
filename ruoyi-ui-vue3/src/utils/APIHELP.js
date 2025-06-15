function generateSignature(params, timestamp, key) {
    // 对参数名进行ASCII排序
    const sortedKeys = Object.keys(params).sort();
    
    // 按排序后的顺序拼接参数名和参数值
    let paramString = '';
    sortedKeys.forEach(key => {
        paramString += key + params[key];
    });
    
    // 拼接timestamp、参数和key
    const stringToSign = timestamp + paramString + key;
    
    // 计算MD5哈希并转换为十六进制
    const md5Hash = CryptoJS.MD5(stringToSign);
    const hexSignature = md5Hash.toString(CryptoJS.enc.Hex).toUpperCase();
    
    return hexSignature;
}

// 使用示例
 export function makeApiRequest(deviceNo, baseUrl, key) {
    // 获取当前时间戳（毫秒）
    const timestamp =new Date().getTime();
    
    // 准备请求参数
    const params = {
        deviceNo: deviceNo
    };
    
    // 生成签名
    const sign = generateSignature(params, timestamp, key);
    
    // 构建请求URL
    let queryParams = `deviceNo=${deviceNo}&timestamp=${timestamp}&sign=${sign}`;
    const requestUrl = `${baseUrl}?${queryParams}`;
    
    console.log("请求URL:", requestUrl);
    
    // 这里可以添加实际发送请求的代码
    fetch(requestUrl)
      .then(response => response.json())
      .then(data => console.log(data))
      .catch(error => console.error('请求错误:', error));
}

// 示例调用
//http://admin.yun-sense.com/v1/getDeviceData
const baseUrl = "http://admin.yun-sense.com/v1/getDeviceData";
const deviceNo = "AMT24112216420021";
const apiKey = "YpMDbau7rxFI7QIkQsQiDLBSnr65OiCU";

makeApiRequest(deviceNo, baseUrl, apiKey);
