import request from "@/utils/request"

const getAllLabel = (param) => {
        return new Promise((resolve, reject) => {
            request({
                url: 'Label/GetAllLabel',
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