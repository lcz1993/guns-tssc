/**
 * 产品类别管理初始化
 */
var Genre = {
    id: "GenreTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Genre.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Genre.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Genre.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加产品类别
 */
Genre.openAddGenre = function () {
    var index = layer.open({
        type: 2,
        title: '添加产品类别',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/genre/genre_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看产品类别详情
 */
Genre.openGenreDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '产品类别详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/genre/genre_update/' + Genre.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除产品类别
 */
Genre.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/genre/delete", function (data) {
            Feng.success("删除成功!");
            Genre.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("genreId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询产品类别列表
 */
Genre.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Genre.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Genre.initColumn();
    var table = new BSTable(Genre.id, "/genre/list", defaultColunms);
    table.setPaginationType("client");
    Genre.table = table.init();
});
