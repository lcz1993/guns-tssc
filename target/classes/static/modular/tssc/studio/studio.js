/**
 * 工作室管理初始化
 */
var Studio = {
    id: "StudioTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Studio.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名字', field: 'name', align: 'center', valign: 'middle', sortable: true},
        // {title: '简介', field: 'synopsis', align: 'center', valign: 'middle', sortable: true},
        // {title: '介绍', field: 'introduce', align: 'center', valign: 'middle', sortable: true},
        {title: '图片', field: 'image', align: 'center', valign: 'middle', sortable: true},
        {title: 'logo', field: 'logo', align: 'center', valign: 'middle', sortable: true},
        {title: '成立时间', field: 'founddate', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Studio.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Studio.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加工作室
 */
Studio.openAddStudio = function () {
    var index = layer.open({
        type: 2,
        title: '添加工作室',
        area: ['50%', '50%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/studio/studio_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看工作室详情
 */
Studio.openStudioDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '工作室详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/studio/studio_update/' + Studio.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除工作室
 */
Studio.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/studio/delete", function (data) {
            Feng.success("删除成功!");
            Studio.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("studioId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询工作室列表
 */
Studio.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Studio.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Studio.initColumn();
    var table = new BSTable(Studio.id, "/studio/list", defaultColunms);
    table.setPaginationType("client");
    Studio.table = table.init();
});
