# Spring-Boot Basic Example
>SpringBoot, Thymeleaf, JPA, H2를 사용한 간단 예제 프로젝트
## Getting Strarted

### Prerequisites
* JDK 1.8
* SpringBoot 2.1.4
* Gradle
* Thymeleaf
* JPA
* H2

### Installing
#### git clone
````bash
//로컬저장소로 설정할 프로젝트 위치로 이동한다.
cd {your directory}

//로컬저장소로 설정한다.
git init

//만약 init을 취소하려면 아래의 명령어를 입력한다.
rm -r .git

//원격저장소와 로컬저장소를 연결한다.
git remote add origin https://github.com/iamjunsulee/springboot-example.git

git pull origin master
````
## Structure 
````bash
main
├── java
│   └── com
│       └── example
│           └── basic
│               ├── BasicApplication.java
│               ├── config
│               │   └── QuerydslConfiguration.java
│               ├── controller
│               │   └── CommentController.java
│               │   └── PostController.java
│               │   └── RestApiController.java
│               ├── dto
│               │   └── Comment.java
│               │   └── CommentDto.java
│               │   └── Post.java
│               │   └── PostDto.java
│               │   └── Search.java
│               ├── repository
│               │   └── CommentRepository.java
│               │   └── PostRepository.java
│               │   └── PostRepositoryCustom.java
│               │   └── PostRepositoryImpl.java
│               └── service
│                   └── CommentService.java
│                   └── CommentServiceImpl.java
│                   └── PostService.java
│                   └── PostServiceImpl.java
└── resources
    ├── application.yml
    ├── import.sql
    ├── static
    └── templates
        ├── add.html
        ├── edit.html
        └── home.html
        └── post.html
 ````
 
