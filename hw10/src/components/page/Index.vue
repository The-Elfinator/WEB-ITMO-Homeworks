<template>
    <div>
        <article v-for="post in sortedPost" v-bind:key="post.id">
            <Post :post="post" :postCommentsCount="getPostCommentsCount(post)"/>
        </article>
    </div>
</template>

<script>
import Post from "@/components/page/Post.vue";

export default {
    name: "Index",
    components: {Post},
    props: ["posts", "commentsCounts"],
    computed: {
        sortedPost: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id)
        }
    },
    methods: {
        getPostCommentsCount: function (post) {
            return this.commentsCounts[post.id];
        }
    }
}
</script>

<style scoped>

</style>
