// 1. 기준에 따른 단순 검색
function selectDate() {
    let keyword = $('#keyword');

    if (keyword.val().trim() === '') {
        location.href = '/';
    } else {
        location.href = '/?keyword=' + keyword.val();
    }
}

function selectPopularity() {
    let keyword = $('#keyword');

    if (keyword.val().trim() === '') {
        location.href = '/?sort=popularity';
    } else {
        location.href = '/?sort=popularity&keyword=' + keyword.val();
    }
}

function selectRecruitDeadLine() {
    let keyword = $('#keyword');

    if (keyword.val().trim() === '') {
        location.href = '/?sort=recruitDeadline';
    } else {
        location.href = '/?sort=recruitDeadline&keyword=' + keyword.val();
    }
}

function selectMaxMember() {
    let keyword = $('#keyword');

    if (keyword.val().trim() === '') {
        location.href = '/?sort=maxMember';
    } else {
        location.href = '/?sort=maxMember&keyword=' + keyword.val();
    }
}

// 2. 디테일 검색 (기준 + 키워드)
function detailSearch(searchType, keyword) {
    if (searchType === 'registerDate' ) {
        if (keyword.trim() === '') {
            location.href = '/';
        } else {
            location.href = '?keyword=' + keyword;
        }
    } else {
        if (keyword.trim() === '') {
            location.href = '?sort=' + searchType;
        } else {
            location.href = '?sort=' + searchType + '&keyword=' + keyword;
        }
    }
}