# orderFree-owner
### 동국대학교 2020밸류업 참여 프로젝트
### 오더프리 사용자 애플리케이션
## 목차
- [프로젝트 주제](#프로젝트-주제)
- [서비스 구성도](#서비스-구성도)
- [사용자 애플리케이션 사용 언어](#사용자-애플리케이션-사용-언어)
- [사용자 애플리케이션 디자인](#사용자-애플리케이션-디자인)
- [REST API 문서](#서버와의-통신을-위한-REST-API-문서)
- [안드로이드 코드구조](#안드로이드-코드구조)
- [사용자 애플리케이션 시연 영상](#애플리케이션-시연-영상)

## 프로젝트 주제
시각장애인분들의 키오스크 사용에 있어서의 불편함을 해결하고 접근성을 개선하기 위한 애플리케이션 기반 서비스 <br>
![subject_img](https://github.com/jryoun1/algorithm-study/blob/master/source/yeon/images/OrderFreeSubject.png)<br>

## 서비스 구성도
서비스 구성도는 아래의 사진과 같다. <br>
![service_Map](https://github.com/jryoun1/algorithm-study/blob/master/source/yeon/images/OrderFreeServiceMap.png)<br>
서비스 구성도를 보다시피 **점주용 애플리케이션**과 **유저용 애플리케이션** 그리고 **서버**가 존재한다. <br>
사용자 애플리케이션에는 사용자 정보들을 입력받고, qr코드를 인식하므로서 해당 가게의 정보들을 받아올 수 있다. <br>
시각장애인들이 주로 사용하는 스마트폰 내의 접근성 기능인 돋보기창이나 VoiceAssitant에 앱의 UI를 최적화하여 메뉴를 읽고, 선택할 수 있다. <br>
메뉴를 선택하여 장바구니에 담고, 장바구니에서 결제창으로 넘어가서 주문을 완료하게 된다면 점주 애플리케이션으로 알림이 가게되며 <br>
점주 애플리케이션에서 음식준비가 완료되어 준비완료를 누를 시, 사용자 애플리케이션에서 알림을 통해 준비가 완료되었음을 확인할 수 있다. <br>
이를 통해 **시각장애인도 키오스크를 사용하지 않고도 개인의 휴대폰으로 주문을하고 알림을 통해 확인을 받을 수 있다.** <br>

## 사용자 애플리케이션 사용 언어
사용자 애플리케이션은 안드로이드 스튜디오에서 Java 언어로 작성되었다. 사용된 API는 다음과 같다. <br>
메뉴 이미지를 불러오기 위해 AWS의 S3 서비스를 사용하였다. <br>
점주용 애플리케이션으로 push notification을 보내기 위해서 FCM을 사용하였다. <br>
가게의 QR코드를 인식하기 위해서 QR코드 인식 API를 사용하였다. <br>
아래의 링크로 들어가면 각각의 API를 사용하는 방법에 대해서 정리해 두었다. <br>
[FCM을 사용하여 푸시알림 보내기 - 1](https://blog.naver.com/jryoun1/222058760991) <br>
[FCM을 사용하여 푸시알림 보내기 - 2](https://blog.naver.com/jryoun1/222058831072) <br>

## 점주용 애플리케이션 디자인
<img src="https://github.com/jryoun1/algorithm-study/blob/master/source/yeon/images/userApp_Design.png" width="500"> <br>

## 서버와의 통신을 위한 REST API 문서
#### 점주용 애플리케이션 REST API
![ownerAppRestApi](https://github.com/jryoun1/algorithm-study/blob/master/source/yeon/images/userappRestApi.png) <br>

## 안드로이드 코드구조
```
UI
|- login
    |- activity
    |- data
|- mainview
    |- activity
    |- data
|- order
    |- CheckOrderActivity.java
    |- CheckOrderAdapter.java
    |- OrderCompleteActivity.java
    |- data
|- Payment
    |- ...
network
|- RetrofitClient.java
|- ServuceApi.java
MyFireBaseMessagingService.java
```
안드로이드 코드 구조는 간략하게 보면 UI, network 폴더와 MyFireBaseMessagingService.java 파일로 구성되어있다. <br>
**MyFirebaseMessagingService.java는** FCM서비스를 이용하여 푸시알림을 받기 위해 작성된 클래스이다. <br>
서버와의 통신은 Retrofit2를 사용하였으며, 이를 위한 코드는 **network 폴더** 에 RetrofitClient와 ServiceApi로 구성되어있다. <br>
**UI 폴더** 는 login, mainview, order, Payment 폴더로 구성되어있으며 login에는 회원가입, 비밀번호찾기, 이메일찾기, 로그인과 같은 기본 기능이 구현되어있다. <br>
**mainview 폴더** 는 qr코드인식, 불러온 메뉴 카테고리별 확인, 메뉴 상세보기 등이 구현되어있다. <br>
**order 폴더** 는 사용자가 자신이 한 주문에 대해서 확인할 수 있는 주문목록 확인이 구현되어있다. <br>

점주용 애플리케이션은 3명에서 구현하였다. <br>
[연정민](https://github.com/jryoun1) - 회원가입, 로그인(+ 자동로그인), 로그아웃, 비밀번호찾기, 이메일찾기, 점주용 애플리케이션으로 알림 보내기 <br>
[김현도](https://github.com/kk090303) - qr인식 후 메뉴불러오기, 카테고리별 메뉴 불러오기, 메뉴 상세보기, 장바구니 <br>
[최고운](https://github.com/gowoon-choi) - 결제하기, Intent 화면 이동 및 데이터 전달 정리, 주문 목록 확인, 개인정보수정 <br>

## 애플리케이션 시연 영상
먼저 사용자애플리케이션에서 QR코드를 인식하는 영상이다. 인식을 마치면 해당 가게의 메뉴정보를 목록형으로 불러온다. <br>
![QRcode](https://github.com/jryoun1/algorithm-study/blob/master/source/yeon/images/QRcode.gif) <br>

QR 코드를 인식하여 메뉴를 불러온 뒤에는 스마트폰의 돋보기 창 기능을 사용하여 메뉴들을 확대해서 확인하고 선택하여 장바구니에 담을 수 있다. <br>
![Menu](https://github.com/jryoun1/algorithm-study/blob/master/source/yeon/images/Menu.gif) <br>

아래의 영상에서 왼쪽에 있는 화면이 점주용 애플리케이션이며, 오른쪽은 사용자 애플리케이션이다. <br>
사용자 애플리케이션으로부터 주문이 들어왔을 때 점주용 애플리케이션에서는 알림을 통해 주문이 들어온 것을 확인하고 <br>
주문이 준비완료 되었을 때 사용자 애플리케이션으로 알림을 보내 준비완료를 알리는 것을 짧게 만든 영상이다. <br>
![userAppGif](https://github.com/jryoun1/algorithm-study/blob/master/source/yeon/images/userAppGif.gif) <br>

-----
아래의 링크를 통해 오더프리 서버와 유저용 애플리케이션의 코드 및 정보가 있는 페이지로 갈 수 있다. <br>
[오더프리 서버](https://github.com/jryoun1/orderFree-server) <br>
[오더프리 점주용 애플리케이션](https://github.com/jryoun1/orderFree-owner) <br>

