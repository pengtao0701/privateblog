/*
 * @Author: pengtao.blog 
 * @Date: 2022-06-17 00:27:37 
 * @Last Modified by: pengtao.blog
 * @Last Modified time: 2022-06-21 11:38:45
 */
import request from "@/utils/request"

const login = (param) => {
    return request({
        url: 'User/Login',
        method: 'post',
        data: JSON.stringify(param)
    });
};

export default {
    login,
};