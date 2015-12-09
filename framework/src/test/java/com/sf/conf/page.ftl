<#compress>
<#recurse doc>
</#compress>

<#macro group>
<table border="1" ${.node.@@attributes_markup}>
	<tr>
	<#list .node.col as c>
	<#if c_index%2 == 0 && c_index!=0>
		</tr><tr>
	</#if>
	<td>
		<#recurse c>
	</td>
	</#list>
	<#assign x=.node.col?size%2>
		<#list 0..x-1 as i>
		<td></td>
		</#list>
	</tr>
</table>
</#macro>

<#macro field>
<input type="text" ${.node.@@attributes_markup} >
</#macro>

<#macro header>
${h.before(.node)}
<#recurse .node>
${h.after(.node)}
</#macro>

<#macro @text>
	${.node?html}
</#macro>

<#macro @element>
	${.node.@@start_tag}
		<#recurse .node>
	${.node.@@end_tag}
</#macro>

