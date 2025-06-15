
import axios from 'axios';

import qs from 'qs';

// 获取直播地址


const request = axios.create({
  baseURL: 'https://open.ys7.com',
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  },
  timeout: 10000,
  transformRequest: [function (data, headers) {
    return qs.stringify(data)
  }]
});





export const getTokens = async ({
  appKey,
  appSecret,
}) => {
  const res = await request({
    url: '/api/lapp/token/get',
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    params: {
      appKey,
      appSecret,
    },
  });
  const { code, data } = res.data;

  if (code !== "200") {
    return
  }

  return data.accessToken;
}




















export async function getLiveUrl(deviceSerial, channelNo = 1) {
  // 1. 获取AccessToken（从服务端）
  // const tokenRes = await axios.get('/api/ys/getToken');
  const accessToken = 'at.7odx4vm7ap2coeas35ifi2767ywtz8te-6m45xdywz0-02fjx2r-lrarkalon';
  const a = { "AppKey": "9d96d9e9439248b3af163466f192ff07", "AccessToken": "at.7odx4vm7ap2coeas35ifi2767ywtz8te-6m45xdywz0-02fjx2r-lrarkalon", "Url": "ezopen://open.ys7.com/FT2988810/1.hd.live" }
  // 2. 获取直播地址
  const liveRes = await axios.post(
    'https://open.ys7.com/api/lapp/v2/live/address/get',
    qs.stringify({
      accessToken,
      deviceSerial,
      channelNo,
      protocol: 2, // 2表示FLV协议
      quality: 2, // 清晰度：1-高清，2-标清
    })
  );
  console.log("🚀 ~ getLiveUrl ~ liveRes:", liveRes)

  return liveRes.data.data.url;
}
//https://open.ys7.com/api/lapp/token/get

/**
 * POST /api/lapp/token/get HTTP/1.1
Host: open.ys7.com
Content-Type: application/x-www-form-urlencoded

appKey=9mqitppidgce4y8n54ranvyqc9fjtsrl&amp;appSecret=096e76501644989b63ba0016ec5776
 */






