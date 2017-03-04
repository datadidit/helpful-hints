var path = require('path');

module.exports = {
  entry: './app/index.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'dist')
  },
  module: {
	  loaders: [
		  {
			  test: /\.css$/,
			  loader: 'css-loader'
		  },
	      {
		        test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
		        loader: 'url-loader',
		        options: {
		          limit: 10000,
		          name: 'fonts/[name].[hash:7].[ext]'
		        }
	      },
	      {
	          test: /\.vue$/,
	          loader: 'vue-loader'
	      }
	  ]
  }
};