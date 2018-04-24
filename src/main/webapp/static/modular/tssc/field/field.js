/**
 * 服务领域管理初始化
 */
var Field = {
    id: "FieldTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Field.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Field.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Field.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加服务领域
 */
Field.openAddField = function () {
    var index = layer.open({
        type: 2,
        title: '添加服务领域',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/field/field_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看服务领域详情
 */
Field.openFieldDetail = function (event) {
    // if (this.check()) {
    var id = $(event.target).parent().prev().val();
    var index = layer.open({
        type: 2,
        title: '服务领域详情',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/field/field_update/' + id
    });
    this.layerIndex = index;
    // }
};

/**
 * 删除服务领域
 */
Field.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/field/delete", function (data) {
            Feng.success("删除成功!");
            Field.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("fieldId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询服务领域列表
 */
Field.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Field.table.refresh({query: queryData});
};
/**
 * 验证名称唯一性
 * @param event
 */
Field.checkName = function(event) {
    var name = $(event.target).val();
    var id = $("#id").val();
    $.ajax({
        url:"${ctxPath}/field/check",
        data:{id:id,name:name},
        type:"post",
        dataType:"json",
        success:function (result) {
            result = result.data;
            if("no" == result){

            }
        },
        fail:function () {

        }
    });
}
$(function () {
    var defaultColunms = Field.initColumn();
    var table = new BSTable(Field.id, "/field/list", defaultColunms);
    table.setPaginationType("client");
    Field.table = table.init();
});
