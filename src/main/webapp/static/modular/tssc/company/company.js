/**
 * 公司管理初始化
 */
var Company = {
    id: "CompanyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Company.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Company.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Company.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加公司
 */
Company.openAddCompany = function () {
    var index = layer.open({
        type: 2,
        title: '添加公司',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/company/company_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看公司详情
 */
Company.openCompanyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '公司详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/company/company_update/' + Company.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除公司
 */
Company.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/company/delete", function (data) {
            Feng.success("删除成功!");
            Company.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("companyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询公司列表
 */
Company.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Company.table.refresh({query: queryData});
};

/**
 * 打开公司品牌编辑
 */
Company.companyBrandEdit = function(){
    var index = layer.open({
        type: 2,
        title: '编辑品牌',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/company/brand_update/'
    });
    this.layerIndex = index;
}
/**
 * 打开公司历史沿革编辑
 */
Company.companyHistoryEdit = function(){
    var index = layer.open({
        type: 2,
        title: '编辑公司历史',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/company/history_update/'
    });
    this.layerIndex = index;
}
/**
 * 打开公司社会责任编辑
 */
Company.companySocialEdit = function(){
    var index = layer.open({
        type: 2,
        title: '编辑公司社会责任',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/company/social_update/'
    });
    this.layerIndex = index;
}
/**
 * 打开公司成长计划编辑
 */
Company.companyGrowupEdit = function(){
    var index = layer.open({
        type: 2,
        title: '编辑公司成长',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/company/growup_update/'
    });
    this.layerIndex = index;
}
/**
 * 打开公司成长计划编辑
 */
Company.companyWelfareEdit = function(){
    var index = layer.open({
        type: 2,
        title: '编辑公司福利',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/company/welfare_update/'
    });
    this.layerIndex = index;
}

$(function () {
    var defaultColunms = Company.initColumn();
    var table = new BSTable(Company.id, "/company/list", defaultColunms);
    table.setPaginationType("client");
    Company.table = table.init();
});
