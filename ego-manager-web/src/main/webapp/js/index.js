$(function(){
    $('#menu').tree({
        onClick: function(node){
            if($('#menu').tree("isLeaf",node.target)){
                var tabs = $("#tabs");
                var tab = tabs.tabs("getTab",node.text);
                if(tab){
                    tabs.tabs("select",node.text);
                }else{
                    var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + node.attributes.url + "'></iframe>";
                    tabs.tabs("add",{
                        title:node.text,
                        closable:true,
                        content:content
                    });
                }
            }
        }
    });
});