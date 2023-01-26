<template>
    <div>
        <article v-for="post in sortedPost" v-bind:key="post.id">
            <Post :post="post"/>
        </article>
    </div>
</template>

<script>
import Post from "@/components/page/Post.vue";

export default {
    name: "Index",
    components: {Post},
    props: ["posts"],
    computed: {
        sortedPost: function () {
            return Object.values(this.posts).sort((a, b) => b.creationTime - a.creationTime)
        }
    }
}
</script>

<style scoped>

</style>
