/*
 * @Author: pengtao.blog 
 * @Date: 2022-08-13 04:41:47 
 * @Last Modified by: pengtao.blog
 * @Last Modified time: 2022-08-13 06:22:25
 */
import request from "@/utils/request"
import url from "./baseurl";
const baseurl = url.baseurl

const fileUpload = (param) => {
    // return request({
    //     headers: {
    //     "Content-Type": "multipart/form-data",
    //     },
    //     url: 'Blog/fileUpload',
    //     method: 'post',
    //     data: param
    // });
    return new Promise((resolve, reject) => {
        request({
            headers: {
                "Content-Type": "multipart/form-data",
            },
            url: 'api/Blog/FileUpload',
            method: 'post',
            data: param
        }).then((res) => {
            resolve(res)
        }, (err) => {
            reject(err)
        })
    })

};



const editBlogSave=(param)=> {
    return new Promise((resolve, reject) => {
        request({
            url: 'api/Blog/EditBlogSave',
            method: 'post',
            data: JSON.stringify(param)
        }).then((res) => {
            resolve(res)
        }, (err) => {
            reject(err)
        })
    })
}

export default {
    fileUpload,
    editBlogSave
};