export class RecipeModel {
    id?: number
    additionDate?: Date;
    name?: string;
    category?: string;
    description?: string;
    ingredients?: Array<string>;
    directions?: Array<string>
    userName?: string;
}