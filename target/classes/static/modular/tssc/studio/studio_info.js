/**
 * 初始化工作室详情对话框
 */
var StudioInfoDlg = {
    studioInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '工作室名称不能为空'
                }
            }
        },
        founddate: {
            validators: {
                notEmpty: {
                    message: '成立时间不能为空'
                }
            }
        },
        // synopsis: {
        //     validators: {
        //         notEmpty: {
        //             message: '简介不能为空'
        //         }
        //     }
        // },
        // introduce: {
        //     validators: {
        //         notEmpty: {
        //             message: '详细介绍不能为空'
        //         }
        //     }
        // },
        // logo: {
        //     validators: {
        //         notEmpty: {
        //             message: '请上传LOGO'
        //         }
        //     }
        // },
        // image: {
        //     validators: {
        //         notEmpty: {
        //             message: '请上传照片'
        //         }
        //     }
        // }
    }
};

/**
 * 清除数据
 */
StudioInfoDlg.clearData = function() {
    this.studioInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StudioInfoDlg.set = function(key, val) {
    this.studioInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StudioInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StudioInfoDlg.close = function() {
    parent.layer.close(window.parent.Studio.layerIndex);
}

/**
 * 收集数据
 */
StudioInfoDlg.collectData = function() {
    // var synopsis = CKEDITOR.instances.synopsis.getData();
    // var introduce = CKEDITOR.instances.introduce.getData();
    // this.studioInfoData.introduce = introduce;
    // this.studioInfoData.synopsis = synopsis;
    this.set('id').set('name').set('founddate').set('logo').set('image');
}

/**
 * 验证数据是否为空
 */
StudioInfoDlg.validate = function () {
    $('#StudioInfoForm').data("bootstrapValidator").resetForm();
    $('#StudioInfoForm').bootstrapValidator('validate');
    return $("#StudioInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
StudioInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/studio/add", function(data){
        Feng.success("添加成功!");
        StudioInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    window.parent.location.reload();
    ajax.set(this.studioInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
StudioInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/studio/update", function(data){
        Feng.success("修改成功!");
        window.parent.Studio.table.refresh();
        StudioInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studioInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("StudioInfoForm", StudioInfoDlg.validateFields);

    // 初始化图片上传
    var url = '/studio/upload'

    var logo = new $WebUpload("logo",url,false);
    logo.init();

    var image = new $WebUpload("image",url,true);
    image.init();
});
