# smallprogram
noob

 http://www.cnblogs.com/eyunhua/p/6502164.html
 
 第一步 
 
 git init
 此命令为添加当前目录下的所有文件到github上，也可以将.换成别的文件或者文件夹名字单个上传
 
 第二步
 git add .
 
 git status  
 
 需要先add . 才能commit
 
 git add readme.txt
 
 可以 在后面-a 提交所有修改（不需要 add 如果-a）
 git commit -m "add distributed"  
 
 
 git remote add origin https://github.com/eyunhua/screen-slide-yearbill.git
 
 最后一步必须执行才能 在浏览器显示提交结果
 
 git pull origin master
 
 git push origin master
 
 强制覆盖 
  git push -u origin master -f 
 
 git pull -f --all

 
rebase in progess 处理

https://segmentfault.com/q/1010000008505539/a-1020000008506625
 git rebase --abort 来取消目前的进程
 
reject处理
http://blog.csdn.net/shiren1118/article/details/7761203