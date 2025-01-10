# Board API

## 엔드포인트

### 1. 게시판 생성 (POST "/boards" )

- **설명** : 새로운 게시판 생성
- **Request Body**:
```json
{
  "name": "게시판 제목"
}
```
- **Response Body**: 없음 <br/><br/><br/>
### 2. 게시판 목록 조회 (GET `"/boards"` )

- **설명** : 새로운 게시판 생성
- **Request Body**: 없음


- **Response Body**: 
```json
[
  {
    "id": 1,
    "name": "게시판 제목"
  }
]
```

# Comment API
## 엔드포인트
### 1. 댓글 작성 (POST `"/comments"`)
- **설명**: 새로운 댓글 작성
- **Request Body**: 
```json
{
  "content": "댓글 내용",
  "author": "작성자 이름",
  "postId": 1
}
```
- **Response Body**: 없음<br/><br/><br/>

### 2. 댓글 조회 (GET `"/comments"`)
- **설명**: 게시글에 달린 댓글 목록 조회
- **Request Param**: `postId` : 댓글을 조회할 게시글 ID


- **Response Body**: 
```json
[
  {
    "id": 1,
    "content": "댓글 내용",
    "author": "작성자 이름",
    "createAt": "2025-01-10T14:30:00",
    "postId": 1
  },
  {
    "id": 2,
    "content": "댓글 내용",
    "author": "작성자 이름",
    "createAt": "2025-01-10T14:30:00",
    "postId": 1
  }
]
```

# Post API
## 엔드포인트
### 1. 게시글 목록 조회 (GET `"/posts"`)
- **설명**: 특정 게시판의 게시글 목록을 조회
- **Request Param**: `boardId` : 게시글을 조회할 게시판의 ID


- **Response Body**:
```json
[
  {
    "id": 1,
    "title": "게시글 제목",
    "content": "게시글 내용",
    "hashTags": [
      {
        "id": 1,
        "name": "해시태그1"
      },
      {
        "id": 2,
        "name": "해시태그2"
      }
    ],
    "createdAt": "2025-01-10T14:30:00",
    "boardId": 1,
    "viewCount": 100,
    "commentCount": 10,
    "recommendCount": 5
  },
  {
    "id": 2,
    "title": "게시글 제목",
    "content": "게시글 내용",
    "hashTags": [
      {
        "id": 1,
        "name": "해시태그1"
      },
      {
        "id": 2,
        "name": "해시태그2"
      }
    ],
    "createdAt": "2025-01-10T14:30:00",
    "boardId": 1,
    "viewCount": 100,
    "commentCount": 10,
    "recommendCount": 5
  }
]
```
<br/><br/><br/>
### 2. 게시글 조회 (GET `"/posts/{postId}"`)
- **설명**: 게시글을 조회
- **Path Variable**: `postId` : 조회할 게시글의 ID(Long 타입, 필수)


- **Response Body**:
```json
{
  "id": 1,
  "title": "게시글 제목",
  "content": "게시글 내용",
  "hashTags": [
    {
      "id": 1,
      "name": "해시태그1"
    },
    {
      "id": 2,
      "name": "해시태그2"
    }
  ],
  "createdAt": "2025-01-10T14:30:00",
  "boardId": 1,
  "viewCount": 100,
  "commentCount": 10,
  "recommendCount": 5
}
```
<br/><br/><br/>
### 3. 게시글 작성 (POST `"/posts"`)
 - **설명**: 특정 게시판에 게시글을 작성
 - **Request Body**:
```json
{
  "title": "게시글 제목",
  "content": "게시글 내용",
  "boardId": 1,
  "hasTags": [
    "해시태그1",
    "해시태그2"
  ]
}
```
<br/><br/><br/>
### 4. 게시글 수정 (PUT `"/posts/{postid}"`)
- **설명**: 특정 게시글을 수정
- **Path Variable**: `postid` : 수정할 게시글의 ID
- **Request Body**:
```json
{
  "title": "게시글 제목",
  "content": "게시글 내용",
  "boardId": 1,
  "hasTags": [
    "해시태그1",
    "해시태그2"
  ]
}
```
<br/><br/><br/>
### 5. 게시글 숨김 (PATCH `"/posts/{postid}/changeisvisible"`)
- **설명**: 특정 게시글의 숨김 설정을 변경
- **Path Variable**: `postid` : 수정할 게시글의 ID
  <br/><br/><br/>
### 6. 게시글 검색 (GET `"/posts/search"`)
- **설명**: 게시글을 제목으로 조회
- **Request Param**: `title` : 조회할 게시글의 제목(String, 필수)

- **Response Body**:
```json
[
  {
    "id": 1,
    "title": "게시글 제목",
    "content": "게시글 내용",
    "hashTags": [
      {
        "id": 1,
        "name": "해시태그1"
      },
      {
        "id": 2,
        "name": "해시태그2"
      }
    ],
    "createdAt": "2025-01-10T14:30:00",
    "boardId": 1,
    "viewCount": 100,
    "commentCount": 10,
    "recommendCount": 5
  },
  {
    "id": 2,
    "title": "게시글 제목",
    "content": "게시글 내용",
    "hashTags": [
      {
        "id": 1,
        "name": "해시태그1"
      },
      {
        "id": 2,
        "name": "해시태그2"
      }
    ],
    "createdAt": "2025-01-10T14:30:00",
    "boardId": 1,
    "viewCount": 100,
    "commentCount": 10,
    "recommendCount": 5
  }
]
```
<br/><br/><br/>
### 7. 게시글 추천 (PATCH `"/posts/{postid}/recommend"`)
- **설명**: 특정 게시글의 추천, 취소 기능
- **Path Variable**: `postid` : 수정할 게시글의 ID
  <br/><br/><br/>
### 8. 게시글 정렬 (PATCH `"/posts/sorted"`)
- **설명**: 게시글을 최신순, 추천순, 조회순으로 정렬, 기본값은 최신순
- **Request Param**: `orderBy`: `viewcount`, `recommendcount`, `recent` 각각 조회순 추천순 최신순을 나타낸다.


- **Response Body**:
```json
[
  {
    "id": 1,
    "title": "게시글 제목",
    "content": "게시글 내용",
    "hashTags": [
      {
        "id": 1,
        "name": "해시태그1"
      },
      {
        "id": 2,
        "name": "해시태그2"
      }
    ],
    "createdAt": "2025-01-10T14:30:00",
    "boardId": 1,
    "viewCount": 100,
    "commentCount": 10,
    "recommendCount": 5
  },
  {
    "id": 2,
    "title": "게시글 제목",
    "content": "게시글 내용",
    "hashTags": [
      {
        "id": 1,
        "name": "해시태그1"
      },
      {
        "id": 2,
        "name": "해시태그2"
      }
    ],
    "createdAt": "2025-01-10T14:30:00",
    "boardId": 1,
    "viewCount": 100,
    "commentCount": 10,
    "recommendCount": 5
  }
]
```