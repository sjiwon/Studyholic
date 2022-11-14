function movePreviousRange() {
    let pageString = '';
    let sortString = '';
    let keywordString = '';

    let search = location.search;
    let queryList = search.split('&');

    if (search.includes('page') && search.includes('sort') && search.includes('keyword')) {
        let pageInfo = queryList[0].split('=')[1];
        let sortInfo = queryList[1].split('=')[1];
        let keywordInfo = queryList[2].split('=')[1];

        if (pageInfo % 10 === 0) {
            pageString = '/?page=' + (pageInfo - 19);
        } else {
            let number = Math.floor(pageInfo / 10 - 1);
            pageString = (number === 0) ? '/?page=1' : '/?page=' + (number + '1');
        }

        sortString = '&sort=' + sortInfo;
        keywordString = '&keyword=' + keywordInfo;
        location.href = pageString + sortString + keywordString;
    } else if (search.includes('page') && !search.includes('sort') && search.includes('keyword')) {
        let pageInfo = queryList[0].split('=')[1];
        let keywordInfo = queryList[1].split('=')[1];

        if (pageInfo % 10 === 0) {
            pageString = '/?page=' + (pageInfo - 19);
        } else {
            let number = Math.floor(pageInfo / 10 - 1);
            pageString = (number === 0) ? '/?page=1' : '/?page=' + (number + '1');
        }

        keywordString = '&keyword=' + keywordInfo;
        location.href = pageString + keywordString;
    } else if (search.includes('page') && search.includes('sort') && !search.includes('keyword')) {
        let pageInfo = queryList[0].split('=')[1];
        let sortInfo = queryList[1].split('=')[1];

        if (pageInfo % 10 === 0) {
            pageString = '/?page=' + (pageInfo - 19);
        } else {
            let number = Math.floor(pageInfo / 10 - 1);
            pageString = (number === 0) ? '/?page=1' : '/?page=' + (number + '1');
        }

        sortString = '&sort=' + sortInfo;
        location.href = pageString + sortString;
    } else if (search.includes('page') && !search.includes('sort') && !search.includes('keyword')) {
        let pageInfo = queryList[0].split('=')[1];

        if (pageInfo % 10 === 0) {
            pageString = '/?page=' + (pageInfo - 19);
        } else {
            let number = Math.floor(pageInfo / 10 - 1);
            pageString = (number === 0) ? '/?page=1' : '/?page=' + (number + '1');
        }

        location.href = pageString;
    }
}

function moveNextRange() {
    let pageString = '';
    let sortString = '';
    let keywordString = '';

    let search = location.search;
    let queryList = search.split('&');

    if (search.includes('page') && search.includes('sort') && search.includes('keyword')) {
        let pageInfo = queryList[0].split('=')[1];
        let sortInfo = queryList[1].split('=')[1];
        let keywordInfo = queryList[2].split('=')[1];

        if (pageInfo % 10 === 0) {
            pageString = '/?page=' + (Math.floor(pageInfo / 10) + '1');
        } else {
            pageString = '/?page=' + (Math.floor(pageInfo / 10 + 1) + '1');
        }

        sortString = '&sort=' + sortInfo;
        keywordString = '&keyword=' + keywordInfo;
        location.href = pageString + sortString + keywordString;
    } else if (!search.includes('page') && search.includes('sort') && search.includes('keyword')) {
        let sortInfo = queryList[0].split('=')[1];
        let keywordInfo = queryList[1].split('=')[1];

        pageString = '/?page=11';
        sortString = '&sort=' + sortInfo;
        keywordString = '&keyword=' + keywordInfo;
        location.href = pageString + sortString + keywordString;
    } else if (search.includes('page') && !search.includes('sort') && search.includes('keyword')) {
        let pageInfo = queryList[0].split('=')[1];
        let keywordInfo = queryList[1].split('=')[1];

        if (pageInfo % 10 === 0) {
            pageString = '/?page=' + (Math.floor(pageInfo / 10) + '1');
        } else {
            pageString = '/?page=' + (Math.floor(pageInfo / 10 + 1) + '1');
        }

        keywordString = '&keyword=' + keywordInfo;
        location.href = pageString + keywordString;
    } else if (search.includes('page') && search.includes('sort') && !search.includes('keyword')) {
        let pageInfo = queryList[0].split('=')[1];
        let sortInfo = queryList[1].split('=')[1];

        if (pageInfo % 10 === 0) {
            pageString = '/?page=' + (Math.floor(pageInfo / 10) + '1');
        } else {
            pageString = '/?page=' + (Math.floor(pageInfo / 10 + 1) + '1');
        }

        sortString = '&sort=' + sortInfo;
        location.href = pageString + sortString;
    } else if (!search.includes('page') && !search.includes('sort') && search.includes('keyword')) {
        let keywordInfo = queryList[0].split('=')[1];

        pageString = '/?page=11';
        keywordString = '&keyword=' + keywordInfo;
        location.href = pageString + keywordString;
    } else if (!search.includes('page') && search.includes('sort') && !search.includes('keyword')) {
        let sortInfo = queryList[0].split('=')[1];

        pageString = '/?page=11';
        sortString = '&sort=' + sortInfo;
        location.href = pageString + sortString;
    } else if (search.includes('page') && !search.includes('sort') && !search.includes('keyword')) {
        let pageInfo = queryList[0].split('=')[1];

        if (pageInfo % 10 === 0) {
            pageString = '/?page=' + (Math.floor(pageInfo / 10) + '1');
        } else {
            pageString = '/?page=' + (Math.floor(pageInfo / 10 + 1) + '1');
        }

        location.href = pageString;
    } else {
        location.href = '/?page=11';
    }
}

function moveIdx(idx) {
    let pageString = '';
    let sortString = '';
    let keywordString = '';

    let search = location.search;
    let queryList = search.split('&');

    if (search.includes('page') && search.includes('sort') && search.includes('keyword')) {
        let sortInfo = queryList[1].split('=')[1];
        let keywordInfo = queryList[2].split('=')[1];

        pageString = '/?page=' + idx;
        sortString = '&sort=' + sortInfo;
        keywordString = '&keyword=' + keywordInfo;
        location.href = pageString + sortString + keywordString;
    } else if (!search.includes('page') && search.includes('sort') && search.includes('keyword')) {
        let sortInfo = queryList[0].split('=')[1];
        let keywordInfo = queryList[1].split('=')[1];

        pageString = '/?page=' + idx;
        sortString = '&sort=' + sortInfo;
        keywordString = '&keyword=' + keywordInfo;
        location.href = pageString + sortString + keywordString;
    } else if (search.includes('page') && !search.includes('sort') && search.includes('keyword')) {
        let keywordInfo = queryList[1].split('=')[1];

        pageString = '/?page=' + idx;
        keywordString = '&keyword=' + keywordInfo;
        location.href = pageString + keywordString;
    } else if (search.includes('page') && search.includes('sort') && !search.includes('keyword')) {
        let sortInfo = queryList[1].split('=')[1];

        pageString = '/?page=' + idx;
        sortString = '&sort=' + sortInfo;
        location.href = pageString + sortString;
    } else if (!search.includes('page') && !search.includes('sort') && search.includes('keyword')) {
        let keywordInfo = queryList[0].split('=')[1];

        pageString = '/?page=' + idx;
        keywordString = '&keyword=' + keywordInfo;
        location.href = pageString + keywordString;
    } else if (!search.includes('page') && search.includes('sort') && !search.includes('keyword')) {
        let sortInfo = queryList[0].split('=')[1];

        pageString = '/?page=' + idx;
        sortString = '&sort=' + sortInfo;
        location.href = pageString + sortString;
    } else {
        pageString = '/?page=' + idx;
        location.href = pageString;
    }
}