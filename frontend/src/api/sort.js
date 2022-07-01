import request from "@/utils/request"

const getAllSort = (param) => {
        return new Promise((resolve, reject) => {
            request({
                url: 'Sort/GetAllSort',
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