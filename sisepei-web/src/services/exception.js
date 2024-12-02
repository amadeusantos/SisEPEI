export class APiException extends Error {
    constructor(status, message) {
        super()
        this.status = status
        this.message = message
    }

    isError() {
        return true
    }
}