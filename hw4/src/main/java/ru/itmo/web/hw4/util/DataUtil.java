package ru.itmo.web.hw4.util;

import ru.itmo.web.hw4.model.Post;
import ru.itmo.web.hw4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov"),
            new User(6, "pashka", "Pavel Mavrin"),
            new User(9, "geranazavr555", "Georgiy Nazarov"),
            new User(11, "tourist", "Gennady Korotkevich")
    );

    private static final List<Post> POSTS = Arrays.asList(
        new Post(1, "Lorem10", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Doloremque, tenetur?", 1),
            new Post(7, "MKOSHP", "Hello everyone\n" +
                    "\n" +
                    "On Sunday, Moscow will host the XX Moscow Team Olympiad — a team competition for schoolchildren, held in Moscow as a qualifying competition for the EKOSHP. The Olympiad was prepared by the Moscow Methodological Commission, which is also known to you from the Moscow Olympiad of Schoolchildren in Programming, the Open Olympiad of Schoolchildren in Programming and the Olympiad of Megacities (rounds 327, 342, 345, 376, 401, 433, 441, 466, 469, 507, 516, 541, 545, 567, 583, 594, 622, 626, 657, 680, 704, 707, 727, 751, 775, 802).\n" +
                    "\n" +
                    "The round will take place on Sunday, October 23, 2022 at 10:50 and will last 2 hours. Pay attention to the non-standard start time of the round. 6 tasks will be offered in each division. The round will be held according to the Codeforces rules and will be rated for both divisions.\n" +
                    "\n" +
                    "Good luck to everyone!", 11),
            new Post(19, "Dear friends", "I remind you that tomorrow is the deadline for the 3rd task. Please don't take on the debt. It will not be possible to somehow close it and will make it more difficult to get a zachyot. Better hand over the tasks tomorrow! Well, hand over 4 + HW, so as not to provoke a queue for the next week.", 1),
            new Post(228, "I love WEB", "I'm here to drink some coffee and fix up the Codeforces\n" +
                    "So, as you see, I've just finished my coffee (-_-)", 9),
            new Post(375, "Algorithms & Data Structures", "Dear users! Here is my channel of lectures on YouTube:\n" +
                    "www.youtube.com/c/pavelmavrin", 6)
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        data.put("posts", POSTS);
        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }

}
