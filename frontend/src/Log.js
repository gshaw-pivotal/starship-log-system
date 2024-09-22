export class Log {
    constructor(id, userId, logDate, posting, location, header, content) {
        this.id = id;
        this.userId = userId;
        this.logDate = logDate;
        this.posting = posting;
        this.location = location;
        this.header = header;
        this.content = content;
    }
}