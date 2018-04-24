/**
 * 招聘管理初始化
 */
var Recruit = {
    id: "RecruitTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Recruit.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '岗位名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '岗位介绍', field: 'content', visible: false, align: 'center', valign: 'middle'},
        {title: '职位类别', field: 'category', align: 'center', valign: 'middle', sortable: true},
        {title: '待遇', field: 'treatment', align: 'center', valign: 'middle', sortable: true},
        {title: '性质', field: 'nature', align: 'center', valign: 'middle', sortable: true},
        {title: '地点', field: 'place', align: 'center', valign: 'middle', sortable: true},
        {title: '人数', field: 'number', align: 'center', valign: 'middle', sortable: true},
        {title: '上班时段', field: 'interval', align: 'center', valign: 'middle', sortable: true},
        {title: '休假制度', field: 'vacation', visible: false, align: 'center', valign: 'middle'},
        {title: '上班时间', field: 'working', align: 'center', valign: 'middle', sortable: true},
        {title: '应聘条件', field: 'condition', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Recruit.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Recruit.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加招聘
 */
Recruit.openAddRecruit = function () {
    var index = layer.open({
        type: 2,
        title: '添加招聘',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/recruit/recruit_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看招聘详情
 */
Recruit.openRecruitDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '招聘详情',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/recruit/recruit_update/' + Recruit.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除招聘
 */
Recruit.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/recruit/delete", function (data) {
            Feng.success("删除成功!");
            Recruit.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("recruitId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询招聘列表
 */
Recruit.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Recruit.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Recruit.initColumn();
    var table = new BSTable(Recruit.id, "/recruit/list", defaultColunms);
    table.setPaginationType("client");
    Recruit.table = table.init();
});
