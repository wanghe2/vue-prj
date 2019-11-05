import axios from 'axios'

class HttpRequest{
    httpRequest (options) {
        const instance = axios.create()
        return instance(options)
      }

    httpRequestConfig(){
        const baseurl='http://localhost:8088/'
        const config = {
            baseURL: baseurl    
        }
        return config
    }
      
    getRequest (options) {
        const url = options.url
        const params = options.params
        const instance = axios.create(this.httpRequestConfig())
        return instance.get(url, { params })
      }
}

export default HttpRequest