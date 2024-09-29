function LogItem(log, onClickAction) {
    let contentExtract = log.content.split("\n")[0];

    if (contentExtract.length > 70) {
        contentExtract = contentExtract.slice(0, 70);
    }

    return (
        <div onClick={() => onClickAction(log.id)}>
            <span className="log-list-date-span">{log.logDate}</span>
            <span> | </span>
            <span>{log.header}</span>
            <br/>
            <span className="log-list-posting-span">{log.posting}</span>
            <span> | </span>
            <span>{log.location}</span>
            <br/>
            <span>{contentExtract}</span>
            <hr/>
        </div>
    );
}

export default LogItem;