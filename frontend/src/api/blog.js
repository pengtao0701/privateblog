import request from "@/utils/request"

const fileUpload = (param) => {
    // return request({
    //     headers: {
    //     "Content-Type": "multipart/form-data",
    //     },
    //     url: 'Blog/fileUpload',
    //     method: 'post',
    //     data: param
    // });
    return new Promise((resolve,reject)=>{
        request({
            headers: {
                    "Content-Type": "multipart/form-data",
                    },
                    url: 'Blog/fileUpload',
                    method: 'post',
                    data: param
        }).then((res)=>{
            resolve(res)
        },(err)=>{
            reject(err)
        })
    })

};

export default {
        fileUpload,
};