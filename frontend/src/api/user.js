/*
 * @Author: pengtao.blog 
 * @Date: 2022-06-17 00:27:37 
 * @Last Modified by: pengtao.blog
 * @Last Modified time: 2022-07-01 01:44:44
 */
import request from "@/utils/request"

const login = (param) => {
    return request({
        url: 'User/Login',
        method: 'post',
        data: JSON.stringify(param)
    });
};

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

const logout = (param) => {
    return request({
        url: 'User/Logout',
        method: 'post',
        data: JSON.stringify(param)
    });
};

export default {
    login,
    logout,
    signin,
    getVcode,
};