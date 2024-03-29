package com.lsy.my_movie_recommendation_system.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lsy.my_movie_recommendation_system.entity.Movie;
import com.lsy.my_movie_recommendation_system.entity.MoviePage;
import com.lsy.my_movie_recommendation_system.entity.User;
import com.lsy.my_movie_recommendation_system.entity.po.UserComment;
import com.lsy.my_movie_recommendation_system.service.MovieRecommendService;
import com.lsy.my_movie_recommendation_system.service.MovieService;
import com.lsy.my_movie_recommendation_system.service.UserCommentService;
import com.lsy.my_movie_recommendation_system.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    UserService userService;
    MovieService movieService;
    MovieRecommendService recommendService;
    UserCommentService commentService;

    @Data
    private static class MoviePageVO {
        @JsonProperty("user")
        User user;
        @JsonProperty("pageIndex")
        Integer pageIndex;
        @JsonProperty("pageSize")
        Integer pageSize;
        @JsonProperty("pageCount")
        Integer pageCount;
    }

    @Data
    private static class MoviePageWithNamePrefixVO {
        @JsonProperty("user")
        User user;
        @JsonProperty("pageIndex")
        Integer pageIndex;
        @JsonProperty("pageSize")
        Integer pageSize;
        @JsonProperty("pageCount")
        Integer pageCount;
        @JsonProperty("namePrefix")
        String namePrefix;
    }

    @Data
    private static class MovieRecommendVO {
        @JsonProperty("userId")
        Integer userId;
        @JsonProperty("pageIndex")
        Integer pageIndex;
    }

    @Data
    private static class PhoneRequestVO {
        @JsonProperty("phone")
        String phone;
    }

    @Autowired
    public UserController(UserService userService, MovieService movieService, MovieRecommendService recommendService, UserCommentService commentService) {
        this.userService = userService;
        this.movieService = movieService;
        this.recommendService = recommendService;
        this.commentService = commentService;
    }

    @PostMapping("/user/checkPhoneAvailability")
    @ResponseBody
    public boolean checkPhoneAvailability(@RequestBody PhoneRequestVO phoneRequest) {
        // 在 UserService 中实现检查手机号是否已被注册的逻辑
        return userService.queryUserExistsByPhone(phoneRequest.getPhone());
    }

    @PostMapping("/user/register")
    public String processAddUser(@RequestParam("phone") String phone,
                                 @RequestParam("password") String password,
                                 @RequestParam("name") String name,
                                 @RequestParam("mood") String mood) {
        // 在此之前，先调用 checkPhoneAvailability 方法来检查手机号是否可用
        User user = new User();
        user.setId(null);
        user.setPhone(phone);
        user.setPassword(password);
        user.setName(name);
        user.setMood(mood);
        userService.addUser(user);
        if (user.getId() != null) {
            return "redirect:/user/registerSuccess.html";
        } else  {
            return "redirect:/user/registerFailed.html";
        }
    }

    // 处理用户登录(分页显示)
    @PostMapping("/user/login")
    public String processLogin(@RequestParam("inputPhone") String phone,
                                @RequestParam("inputPassword") String password,
                                Model model) {
        String rightPassword = userService.selectUserPasswordByPhone(phone);
        if (password != null && password.equals(rightPassword)) {
            User user = userService.selectUserAllInfoByPhone(phone);
            Integer pageIndex = 0;
            Integer pageSize = 12;
            MoviePage moviePage = movieService.selectMovieByPage(pageIndex, pageSize);
            model.addAttribute("user", user);
            model.addAttribute("moviePage", moviePage);
            return "movie/show_by_page";
        } else  {
            return "redirect:/user/loginFailed.html";
        }
    }

    // 处理用户登录(分页显示)
    @PostMapping("/user/showMovieByPage")
    public String showMovieByPage(@RequestBody MoviePageVO moviePageVO, Model model) {
        Integer pageIndex = moviePageVO.getPageIndex();
        Integer pageCount = moviePageVO.getPageCount();
        MoviePage moviePage;
        if (pageIndex != null && pageCount != null && pageIndex >= 0 && pageIndex < pageCount) {
            moviePage = movieService.selectMovieByPage(pageIndex, moviePageVO.getPageSize());
        } else  {
            moviePage = movieService.selectMovieByPage(0, moviePageVO.getPageSize());
        }
        model.addAttribute("moviePage", moviePage);
        return "movie/show_by_page::movieList";
    }

    // 处理用户查看
    @PostMapping("/user/showMovieByID")
    public String processShowMovieByID(@RequestParam("movieId") Integer movieID, Model model) {
        Movie movie = movieService.selectMovieByID(movieID);
        model.addAttribute("movie", movie);
        return "movie/show_by_id";
    }

    @PostMapping("/user/redirectToHomePage")
    public String redirectToMovieHomePage(@RequestParam("userId") Integer userId, Model model) {
        User user = null;
        if (userId != null) {
            user = userService.selectUserAllInfoByUserID(userId);
        }
        Integer pageIndex = 0;
        Integer pageSize = 12;
        MoviePage moviePage = movieService.selectMovieByPage(pageIndex, pageSize);
        model.addAttribute("user", user);
        model.addAttribute("moviePage", moviePage);
        return "movie/show_by_page";
    }

    @PostMapping("/user/redirectToUserHome")
    public String redirectToUserHomePage(@RequestParam("userId") Integer userId, Model model) {
        User user = null;
        if (userId != null) {
            user = userService.selectUserAllInfoByUserID(userId);
        }
        model.addAttribute("user", user);
        return "user/home";
    }

    @PostMapping("/user/getHeadMovieByNamePrefix")
    @ResponseBody
    public List<Movie> getHeadMovieByNamePrefix(@RequestParam("movieNamePrefix") String movieNamePrefix) {
        Integer pageSize = 12;
        return movieService.selectMovieByLikelyNameAndLimitN(movieNamePrefix, pageSize);
    }

    @PostMapping("/user/getHeadMovieByNamePrefixAndPage")
    public String getHeadMovieByNamePrefixAndPage(@RequestParam("moviePrefixNameInput") String movieNamePrefix, @RequestParam("userIdInSelectMovieNameByPrefix") Integer userId, Model model) {
        Integer pageIndex = 0;
        Integer pageSize = 12;
        User user = userService.selectUserAllInfoByUserID(userId);
        MoviePage moviePage =  movieService.selectMovieByLikelyNameAndPage(movieNamePrefix, pageIndex, pageSize);
        model.addAttribute("user", user);
        model.addAttribute("namePrefix", movieNamePrefix);
        model.addAttribute("moviePage", moviePage);
        return "movie/show_by_name";
    }

    @PostMapping("/user/showMovieByNamePrefixAndPage")
    public String showMovieByNamePrefixAndPage(@RequestBody MoviePageWithNamePrefixVO moviePageVO, Model model) {
        Integer pageIndex = moviePageVO.getPageIndex();
        Integer pageCount = moviePageVO.getPageCount();
        String namePrefix = moviePageVO.getNamePrefix();
        MoviePage moviePage;
        if (pageIndex != null && pageCount != null && pageIndex >= 0 && pageIndex < pageCount) {
            moviePage = movieService.selectMovieByLikelyNameAndPage(namePrefix, pageIndex, moviePageVO.getPageSize());
        } else  {
            moviePage = movieService.selectMovieByLikelyNameAndPage(namePrefix, 0, moviePageVO.getPageSize());
        }
        User user = moviePageVO.getUser();
        model.addAttribute("user", user);
        model.addAttribute("moviePage", moviePage);
        return "movie/show_by_name::movieList";
    }

    @PostMapping("/user/movieRecommend")
    public String processMovieRecommend(@RequestParam("userId") Integer userId, Model model) {
        User user = userService.selectUserAllInfoByUserID(userId);
        Integer pageIndex = 0;
        Integer pageSize = 12;
        List<Movie> movieList = movieService.movieRecommend(userId, pageIndex, pageSize);
        model.addAttribute("user", user);
        model.addAttribute("movieList", movieList);
        return "movie/recommend";
    }

    @PostMapping("/user/updateMovieRecommend")
    public String processUpdateMovieRecommend(@RequestBody MovieRecommendVO userMovieInfo, Model model) {
        Integer pageIndex = userMovieInfo.getPageIndex();
        Integer pageSize = 12;
        Integer userId = userMovieInfo.getUserId();
        List<Movie> movieList = movieService.movieRecommend(userId, pageIndex + 1, pageSize);
        model.addAttribute("movieList", movieList);
        return "movie/recommend::movieList";
    }

    @PostMapping("/user/updateBasicInfo")
    public String processUpdateBasicInfo(@RequestParam("phone") String phone,
                                         @RequestParam("password") String password,
                                         @RequestParam("name") String name,
                                         @RequestParam("mood") String mood,
                                         @RequestParam("userId") Integer userId,
                                         Model model) {
        User user = new User();
        user.setId(userId);
        user.setPhone(phone);
        user.setPassword(password);
        user.setName(name);
        user.setMood(mood);
        Boolean ok = userService.updateUserInfo(user);
        if (ok) {
            model.addAttribute("messageFromUpdateBasicInfo", "修改基本信息成功");
        } else {
            model.addAttribute("messageFromUpdateBasicInfo", "修改基本信息失败");
        }
        model.addAttribute("user", user);
        return "user/home";
    }

    @PostMapping("/user/recommendByUserCF")
    public String processRecommendMovieByUserCF(@RequestParam("userId") Integer userId, Model model) {
        User user = userService.selectUserAllInfoByUserID(userId);
        Integer pageIndex = 0;
        Integer pageSize = 36;
        Integer minCommendRecordInNeedForUser = 20;
        Integer minCommendRecordInNeedForMovie = 5;
        List<Movie> movieList = recommendService.recommendMovieByPageByUserCf(userId, pageIndex, pageSize, minCommendRecordInNeedForUser, minCommendRecordInNeedForMovie);
        model.addAttribute("user", user);
        model.addAttribute("movieList", movieList);
        return "movie/recommend";
    }

    @PostMapping("/user/updateUserCFRecommendation")
    public String processUpdateUserCFRecommendation(@RequestBody MovieRecommendVO userMovieInfo, Model model) {
        Integer pageIndex = userMovieInfo.getPageIndex();
        Integer pageSize = 36;
        Integer minCommendRecordInNeedForUser = 20;
        Integer minCommendRecordInNeedForMovie = 5;
        Integer userId = userMovieInfo.getUserId();
        if (pageIndex < 3) {
            ++pageIndex;
        } else {
            pageIndex = 0;
        }
        List<Movie> movieList = recommendService.recommendMovieByPageByUserCf(userId, pageIndex, pageSize, minCommendRecordInNeedForUser, minCommendRecordInNeedForMovie);
        model.addAttribute("movieList", movieList);
        return "movie/recommend::movieList";
    }

    @PostMapping("/user/addComment")
    public String processAddComment(@RequestParam("userId") Integer userId,
                                    @RequestParam("movieId") Integer movieId,
                                    @RequestParam("rating") Double score,
                                    @RequestParam("commentText") String comment,
                                    Model model) {
        UserComment userComment = new UserComment();
        userComment.setUserId(userId);
        userComment.setMovieId(movieId);
        userComment.setScore(score);
        userComment.setComment(comment);
        userComment.setTimestamp(System.currentTimeMillis());
        Boolean ok = commentService.addComment(userComment);
        if (ok) {
            model.addAttribute("stateMessageForAddComment", "添加评论成功");
        } else {
            model.addAttribute("stateMessageForAddComment", "添加评论失败");
        }
        Movie movie = movieService.selectMovieByID(movieId);
        model.addAttribute("movie", movie);
        return "movie/show_by_id";
    }
}
