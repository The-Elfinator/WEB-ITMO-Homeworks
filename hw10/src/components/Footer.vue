<template>
    <footer>
        <a href="/">Codeforces</a> &copy; 2022 by Mike Mirzayanov
        <p>
          Current count of registered users: {{ usersCount }}
        </p>
        <p>
          Current count of posts: {{ postsCount }}
        </p>
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["usersCount", "postsCount"]
}
</script>

<style scoped>

</style>