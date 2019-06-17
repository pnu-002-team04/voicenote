  # VoiceNote - 소프트웨어응용설계 팀프로젝트 (월요일 분반 4조)

학과 | 학번 | 성명
---- | ---- | ---- 
정보컴퓨터공학 |201624642 |김남규

 
 
## 개요

﻿VoiceNote(이하 VN)는 사용자가 수업을 듣고 있는 강의를 녹음한 음성 파일을 텍스트로 변환 후 DOCX와 PDF 문서 양식으로 저장해주는 프로그램입니다.
 
## 내용

—————————————————Program Configulation————————————


좌측 메뉴 영역 : 메인 화면 영역

 - File Upload : ‘upload file’ 버튼 
	=> 변환시킬 음성파일 업로드
 - Erase Noise : ‘erase noise’ 버튼
	=> 음성파일의 noise를 제거
 - Preview : ‘KOR / ENG Check’ 체크박스, Summary 텍스트박스, 'get text' 버튼, 'Summarization' 버튼, 'preview' 버
	=> 변환시킬 음성파일이 한국어 / 영어인지 구분, 요약 줄 수 입력, 음성을 텍스트로 바꾸기, 요약하기, 미리 보
 - Save : ‘Save as..’ 버튼 
	=> 변환된 문서파일 저장


—————————————————How to use——————————————————


1. VN을 실행시킨다.
2. 최초 화면으로 ‘File Upload’ 메뉴의 장면이 뜨고 메인 화면의 ‘Upload File’ 버튼을 누른다.
3. 파일 탐색기가 뜨면 사용자가 변환하고 싶은 음성파일을 로컬 저장소에서 선택한다.
4. 선택이 완료되면 좌측 메뉴에서 ‘Erase Noise’를 누른다.
5. 음성파일의 Noise를 제거하기 위 메인 화면의 ‘erase noise’ 버튼을 누르고 좌측 메뉴에서 ‘Preview’를 누른다.
6. 메인화면에 변환하고 싶은 음성파일이 ‘한국어’라면 ‘KOR / ENG Check’에 체크하고, ‘영어’라면 체크를 해제한다.
7. 체크박스 하단의 텍스트 박스에 사용자가 원하는 요약할 줄 수를 입력한다.
8. 6, 7단계가 완료되면 'get text’ 버튼을 눌러 음성을 텍스트로 변환한다.
9. 변환이 완료되고 'Summarization' 버튼이 활성화되면 텍스트 요약을 위해 버튼을 누른다.
10. 요약이 완료되고 'preview' 버튼이 활성화되면 버튼을 누른다.
11. 미리보기 창이 뜨는 것을 확인하고 변환 및 요약된 내용을 확인한다.
12. 확인이 완료되면 좌측 메뉴에서 ‘Save’를 누른다.
13. 메인 화면의 ‘Save as..’ 버튼을 눌러 변환 및 요약된 문서를 저장할 위치를 정한다.


—————————————————————————————————————————


## 발표자료 
* [4조 최종발표자료](https://docs.google.com/presentation/d/1wOIi1PN_HzzbrFe8FhQwdOcHBAo5RbS4MbjSNuGLmwg/edit?usp=sharing) 

## 사용 모듈 및 환경 

번호 | 이름
---- | ---- 
---- | 모듈
1 | sklearn
2 | speech_recognition
3 | google cloud sdk
4 | 
---- | ----
---- | 환경
1 | Python 3.5
2 | java 8
3 | User Interface : javafx (for desktop and mobile application)
4 | windows 10
