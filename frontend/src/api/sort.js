/*
 * @Author: pengtao.blog 
 * @Date: 2022-08-13 04:40:48 
 * @Last Modified by: pengtao.blog
 * @Last Modified time: 2022-08-13 06:22:07
 */
import request from "@/utils/request"

const getAllSort = (param) => {
        return new Promise((resolve, reject) => {
            request({
                url: 'api/Sort/GetAllSort',
                method: 'get',
            }).then((res) => {
                resolve(res)
            }, (err) => {
                reject(err)
            })
        })
    }

    export default{
        getAllSort
    }