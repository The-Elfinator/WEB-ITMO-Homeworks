<#-- @ftlvariable name="postComment" type="ru.itmo.wp.domain.Comment" -->
<#-- @ftlvariable name="commentForm" type="ru.itmo.wp.form.CommentCredentials" -->
<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#import "commons.ftlh" as c>

<@c.page>
    <@c.post post/>
    <#if user??>
        <div class="comment-form">
            <div class="body">
                <form method="post" action="">
                    <input type="hidden" name="action" value="post"/>
                    <div class="field">
                        <div class="name">
                            <label for="text">Comment: </label>
                        </div>
                        <div class="value">
                            <textarea id="text" name="text">${commentForm.text!}</textarea>
                        </div>
                        <@c.error "commentForm.text"/>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Comment">
                    </div>
                </form>
            </div>
        </div>
    </#if>
    <#list post.comments as comment>
        <@c.comment comment/>
    </#list>
</@c.page>
