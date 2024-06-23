毕设项目

支撑推荐系统的数据集来自：https://files.grouplens.org/datasets/movielens/ml-latest-small.zip

该数据集包含了600多个用户，对9000多部电影的评分信息，评分信息达100000+条。

![Image text](https://github.com/AsunaLSYLXY/MyMovieRecommendationSystem/blob/main/img/%E6%95%B0%E6%8D%AE%E9%9B%86%E8%AF%B4%E6%98%8E.png)

其中 links.csv 记录了电影地址，movies.csv 记录了电影的基本信息，ratings.csv 记录了用户的对电影的评分信息，tags.csv 记录了用户对电影打的标签。

原始电影信息如下。
  
![Image text](https://github.com/AsunaLSYLXY/MyMovieRecommendationSystem/blob/main/img/%E5%8E%9F%E5%A7%8B%E7%94%B5%E5%BD%B1%E4%BF%A1%E6%81%AF.png)
  
这些信息无法很好的满足电影显示的需求，因此采用爬虫手段，获取网页信息，爬虫手段参考：https://blog.csdn.net/qq_40195778/article/details/128763604

经过爬虫补充的电影信息如下：

![Image text](https://github.com/AsunaLSYLXY/MyMovieRecommendationSystem/blob/main/img/%E7%94%B5%E5%BD%B1%E6%96%87%E6%9C%AC%E4%BF%A1%E6%81%AF.png)

![Image text](https://github.com/AsunaLSYLXY/MyMovieRecommendationSystem/blob/main/img/%E7%94%B5%E5%BD%B1%E5%9B%BE%E7%89%87%E4%BF%A1%E6%81%AF.png)

最终将这些信息录入MySQL进行保存。

使用了UserCF作为推荐算法，UserCF的代码逻辑可以参考：https://www.jianshu.com/p/ec3de12db6e7

考虑到如果用户评分不多，使用UserCF算法不能很好的进行推荐，因此在用户评论次数较少的时候（小于20个，因为数据集里面参与评论的用户至少评论20条），对于电影按照被评论次数为第一关键字，评分平均数为第二关键字降序推荐给用户。当评论次数达到阈值的时候，正常采用UserCF算法进行推荐。
