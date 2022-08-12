/*
 * @Author: pengtao.blog 
 * @Date: 2022-08-13 04:41:04 
 * @Last Modified by: pengtao.blog
 * @Last Modified time: 2022-08-13 06:22:12
 */
import request from "@/utils/request"

const getAllLabel = (param) => {
        return new Promise((resolve, reject) => {
            request({
                url: 'api/Label/GetAllLabel',
                method: 'get',
            }).then((res) => {
                resolve(res)
            }, (err) => {
                reject(err)
            })
        })
    }

    export default{
        getAllLabel
    }