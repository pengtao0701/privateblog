import request from "@/utils/request"

const signin = (param) => {
    return request({
        url:'User/Signin',
        method: 'post',
        data: JSON.stringify(param)
    });
};

const getVcode  = (param) => {
  return request({
      url:'User/Getvcode',
      method: 'get',
      data: JSON.stringify(param)
  });
};

export default {
  signin,
  getVcode,
};