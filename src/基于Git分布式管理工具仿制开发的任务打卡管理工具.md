# 基于Git分布式管理工具仿制开发的任务打卡管理工具

## 主体细节敲定

1. **颜色和强调色确认**

   - 系统提示信息使用**黄色**
   - 输入后的信息确认使用**蓝色**
   - 成功信息确认使用**绿色**
   - 操作错误使用**红色**

   ```java
   private static String getFormatLogString(String content, int colour, int type) {
           boolean hasType = type != 1 && type != 3 && type != 4;
           if (hasType) {
               return String.format("\033[%dm%s\033[0m", colour, content);
           } else {
               return String.format("\033[%d;%dm%s\033[0m", colour, type, content);
           }
       }
   
   // 使用方法如下
   System.out.println(getFormatLogString("输出内容", 33, 0));
   // 31-红色，32-绿色，33-黄色，34-蓝色，35-紫色
   ```

2. 

