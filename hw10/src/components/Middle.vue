<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="posts" :commentsCounts="getCommentsCount(posts)"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <Users v-if="page === 'Users'" :users="users"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./page/Index";
import Enter from "./page/Enter";
import WritePost from "./page/WritePost";
import EditPost from "./page/EditPost";
import Register from "./page/Register";
import Users from "./page/Users";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index"
        }
    },
    components: {
        Users,
        WritePost,
        Enter,
        Index,
        Sidebar,
        EditPost,
        Register
    },
    props: ["posts", "users", "comments"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        }
    },
    methods: {
        getCommentsCount: function (posts) {
            let commentsCount = {};
            console.log(Object.keys(posts));
            //posts = JSON.parse(JSON.stringify(posts));
            // console.log(Object.values(posts));
            for (let key in Object.keys(posts)) {
                const post = Object.values(posts)[key];
                commentsCount[post.id] = Object.values(this.comments).filter(c => c.postId === post.id).length;
            }
            return commentsCount;
        }
    },
    beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page)
    }
}
</script>

<style scoped>

</style>
