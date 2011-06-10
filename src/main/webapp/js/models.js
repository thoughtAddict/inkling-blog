
var PostModel = Backbone.Model.extend({
    defaults: {
    	"postId":"",
    	"author":"",
    	"dateCreated":"",
    	"title":"",
    	"content":"", 
    	"categories":[]
    }
}); 

var PostCollection = Backbone.Collection.extend({
    model: PostModel
});