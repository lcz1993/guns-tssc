/**
 * 新闻管理初始化
 */
var News = {
    id: "NewsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
News.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
News.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        News.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加新闻
 */
News.openAddNews = function () {
    var index = layer.open({
        type: 2,
        title: '添加新闻',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/news/news_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看新闻详情
 */
News.openNewsDetail = function () {
    var index = layer.open({
        type: 2,
        title: '新闻详情',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/news/news_update/' + News.seItem.id
    });
    this.layerIndex = index;
};

/**
 * 删除新闻
 */
News.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/news/delete", function (data) {
            Feng.success("删除成功!");
            News.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("newsId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询新闻列表
 */
News.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    News.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = News.initColumn();
    var table = new BSTable(News.id, "/news/list", defaultColunms);
    table.setPaginationType("client");
    News.table = table.init();
});
