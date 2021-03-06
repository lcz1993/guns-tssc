/**
 * 人员管理初始化
 */
var Personnel = {
    id: "PersonnelTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Personnel.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Personnel.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Personnel.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加人员
 */
Personnel.openAddPersonnel = function () {
    var index = layer.open({
        type: 2,
        title: '添加人员',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/personnel/personnel_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看人员详情
 */
Personnel.openPersonnelDetail = function (id) {
        var index = layer.open({
            type: 2,
            title: '人员详情',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/personnel/personnel_update/' + id
        });
        this.layerIndex = index;
};

/**
 * 删除人员
 */
Personnel.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/personnel/delete", function (data) {
            Feng.success("删除成功!");
            Personnel.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("personnelId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询人员列表
 */
Personnel.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Personnel.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Personnel.initColumn();
    var table = new BSTable(Personnel.id, "/personnel/list", defaultColunms);
    table.setPaginationType("client");
    Personnel.table = table.init();
});
