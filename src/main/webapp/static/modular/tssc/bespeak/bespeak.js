/**
 * 服务预约管理初始化
 */
var Bespeak = {
    id: "BespeakTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Bespeak.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名字', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '职称', field: 'title', align: 'center', valign: 'middle', sortable: true},
        {title: '邮箱', field: 'email', align: 'center', valign: 'middle', sortable: true},
        {title: '电话', field: 'tel', align: 'center', valign: 'middle', sortable: true},
        {title: '公司名称', field: 'companyName', align: 'center', valign: 'middle', sortable: true},
        {title: '公司网站', field: 'companyWeb', align: 'center', valign: 'middle', sortable: true},
        {title: '预算', field: 'budget', align: 'center', valign: false, sortable: true},
        {title: '品牌', field: 'brand', align: 'center', valign: 'middle', sortable: true},
        {title: '服务', field: 'service', visible: false, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remark', visible: false, align: 'center', valign: 'middle'},
        {title: '是否受理', field: 'isaccept', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Bespeak.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Bespeak.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加服务预约
 */
Bespeak.openAddBespeak = function () {
    var index = layer.open({
        type: 2,
        title: '添加服务预约',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bespeak/bespeak_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看服务预约详情
 */
Bespeak.openBespeakDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '服务预约详情',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bespeak/bespeak_update/' + Bespeak.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除服务预约
 */
Bespeak.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bespeak/delete", function (data) {
            Feng.success("受理成功!");
            Bespeak.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bespeakId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询服务预约列表
 */
Bespeak.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Bespeak.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Bespeak.initColumn();
    var table = new BSTable(Bespeak.id, "/bespeak/list", defaultColunms);
    table.setPaginationType("client");
    Bespeak.table = table.init();
});
