<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>
<@c.page>
    <div class="user">
        <#if handle??>
            <#assign u = c.findBy(users, "handle", handle)!/>
        <#elseif user_id??>
            <#assign u = c.findBy(users, "id", user_id)!/>
        </#if>

        <#if u?has_content>
            <div class="title">
                <@c.userlink u></@c.userlink>
            </div>
            <#assign fields=[
            {"topic": "id: ", "value": "${u.id}"},
            {"topic": "handle: ", "value": "${u.handle}"},
            {"topic": "name: ", "value": "${u.name}"},
            {"topic": "postsCount: ", "value": "${postsCount(u)}"}
            ]/>
            <ul>
                <#list fields as field>
                    <li>
                        <#if field.topic == "postsCount: " && (field.value != "0")>
                            ${field.topic}<a href="/posts?user_id=${u.id}">${field.value}</a>
                        <#else>
                            ${field.topic}${field.value}
                        </#if>
                    </li>
                </#list>
            </ul>
            <img src="/img/no-title.jpg" alt="No-title">
            <div class="image"></div>
        <#else>
            No such user
        </#if>
    </div>
    <div class="arrows">
        <#assign prevHandle = c.getPrevHandle(u)/>
        <#assign nextHandle = c.getNextHandle(u)/>
        <#if prevHandle != "">
            <a href="/user?handle=${prevHandle}">&longleftarrow;</a>
        <#else>
            &longleftarrow;
        </#if>
        <#if nextHandle != "">
            <a href="user?handle=${nextHandle}">&longrightarrow;</a>
        <#else>
            &longrightarrow;
        </#if>
    </div>
</@c.page>

<#function postsCount u>
    <#assign cnt=0/>
    <#list posts as post>
        <#if post.userId == u.id>
            <#assign cnt++/>
        </#if>
    </#list>
    <#return cnt/>
</#function>
