<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <template>
            <article>
                <div class="title"></div>
                <div class="information"></div>
                <div class="body"></div>
            </article>
        </template>
        <div class="noData">
            <article>
                No articles
            </article>
        </div>
    </div>
    <script>

        const success = function success($noData) {
            return function (response) {
                if (response["articles"].length !== 0) {
                    response["articles"].forEach(function (article) {
                        $noData.hide();
                        const $article = $($(".articles template").prop("content")).clone();
                        $article.find(".title").text(article["title"]);
                        $article.find(".information").text("Created at " + article["creationTime"]);
                        $article.find(".body").text(article["text"]);
                        $article.find("article").attr("article-id", article["id"]);
                        $(".articles").append($article);
                    });
                    $(".articles article").click(function () {
                        const successInForArt = function getSuccess() {
                            return function (response) {
                                notify("This article was created by " + response["article-user"].login);
                            };
                        }

                        doAjax({action: "findUserLogin", articleId: $(this).attr("article-id")}, successInForArt());
                    });
                } else {
                    $noData.find("article").text("No articles");
                }
            };
        };

        $(function () {
            const $noData = $(".articles .noData");
            $noData.find("article").text("Loading...");

            doAjax({action: "findAll"}, success($noData));
        });
    </script>
</@c.page>
