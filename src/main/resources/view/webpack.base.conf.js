const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const PATHS = {
    src: path.join(__dirname, './src'),
    build: path.join(__dirname, './build'),
    js: path.join(__dirname, './src/entry'),
    vendor: [
        'babel-polyfill',
    ]
}

module.exports = {
    externals: {
        paths: PATHS,
    },
    entry: {
        signup: `${PATHS.js}/signup.js`,
        login: `${PATHS.js}/login.js`,
        crud: `${PATHS.js}/crud.js`,
    },
    output: {
        library: 'crud',
        filename: 'js/[name].js',
        path: PATHS.build,
        publicPath: '/',
    },
    optimization: {
        minimizer: [ new UglifyJsPlugin({
            include: /\.js$/,
        })],
    },
    module: {
        rules: [{
            test: /\.js$/,
            loader: 'babel-loader',
            exclude: '/node_modules/',
        }, {
            test: /\.(png|jpg|gif|svg)$/,
            loader: 'file-loader',
            exclude: '/node_modules/',
            options: {
                name: '[name].[ext]'
            }
        }, {
            test: /\.scss$/,
            use: [
                'style-loader',
                MiniCssExtractPlugin.loader,
                {
                    loader: 'css-loader',
                    options: {
                        sourceMap: true,
                    }
                }, {
                    loader: 'postcss-loader',
                    options: {
                        sourceMap: true,
                    }
                }, {
                    loader: 'sass-loader',
                    options: {
                        sourceMap: true,
                    }
                }
            ]
        }, {
            test: /\.css$/,
            use: [
                MiniCssExtractPlugin.loader,
                {
                    loader: 'css-loader',
                    options: {
                        sourceMap: true,
                    }
                }, {
                    loader: 'postcss-loader',
                    options: {
                        sourceMap: true,
                    }
                }
            ]
        },]
    },
    plugins: [
        new MiniCssExtractPlugin({
            filename: 'css/[name].css',
        }),
        new HtmlWebpackPlugin({
            hash: false,
            template: `${PATHS.src}/crud.html`,
            filename: './crud.html',
            inject: false,
        }),
        new HtmlWebpackPlugin({
            hash: false,
            template: `${PATHS.src}/signup.html`,
            filename: './signup.html',
            inject: false,
        }),
        new HtmlWebpackPlugin({
            hash: false,
            template: `${PATHS.src}/login.html`,
            filename: './login.html',
            inject: false,
        }),
        new HtmlWebpackPlugin({
            hash: false,
            template: `${PATHS.src}/error.html`,
            filename: './error.html',
            inject: false,
        }),
        new HtmlWebpackPlugin({
            hash: false,
            template: `${PATHS.src}/oauth2Redirect.html`,
            filename: './oauth2Redirect.html',
            inject: false,
        }),
        new CopyWebpackPlugin([
            { from: `${PATHS.src}/img`, to: `${PATHS.build}/img` },
            { from: `${PATHS.src}/js/lib`, to: `${PATHS.build}/js/lib`},
        ])
    ]
}