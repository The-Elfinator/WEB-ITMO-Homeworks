<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>


<@c.page>
    <#assign u=c.findBy(users, "id", user_id)!/>
    <#if u?has_content>
        <#list posts?reverse as post>
            <#if post.userId == user_id>
                <@c.writeText post/>
            </#if>
        </#list>
    <#else>
        No such user
    </#if>
</@c.page>