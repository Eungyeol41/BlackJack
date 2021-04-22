# BlackJack

## 우리도할 수 있다!!
## 성공적인 협업 프로젝트를 위한 git 튜토리얼

### 프로젝트 만든사람
* 그냥 만들면됨
* Settings -> Manage access -> collaborator 추가함

### collaborator 초대받은 사람
* 프로젝트를 다운받을 파일을 준비하고 그곳에서 bash 창 열기
* git clone <다운받을 repo주소>

### branch 만들고 push 하기
* git branch <브랜치이름>
* git checkout <브랜치이름>
* 코드수정하고
* git add .
* git commit -m "코멘트"
* git push --set-upstream origin <브랜치이름>
*     또는 git push -u origin <브랜치이름>
*     선생님이 git push origin <브랜치이름> 으로해도 문제없었음 왜지? 

### pull 하기
* git pull <repo주소> main
