<#compress>
<#recurse doc>
</#compress>

<#macro field>
<input type="text" ${.node.@@attributes_markup} >
</#macro>

<#macro @text>
	${.node?html}
</#macro>

<#macro @element>
	${.node.@@start_tag}
		<#recurse .node>
	${.node.@@end_tag}
</#macro>